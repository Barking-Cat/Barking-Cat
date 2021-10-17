import React, { useState } from 'react';
import axios from 'axios';
import { NextPage } from 'next';
import { useForm } from 'react-hook-form';
import { useRouter } from 'next/router';

interface AdoptionInput {
  residence: string;
  phone: string;
  income: string;
  residenceType: string;
  inmateNumber: string;
  pet: string;
  experience: string;
  adoptReason: string;
}

interface AdoptionRequest extends AdoptionInput {}

const Adoption: NextPage = () => {
  const router = useRouter();
  const [error, setError] = useState<boolean>(false);
  const { register } = useForm<AdoptionInput>({ mode: 'all' });
  return (
    <div>
      <form>
        <h1>입양 신청하기</h1>
        <h3>거주지</h3>
        <input
          type="text"
          placeholder="서울시 마포구"
          {...register('residence', {
            required: '거주지를 입력해 주세요.',
          })}
        ></input>
        <h3>연락처</h3>
        <input
          type="text"
          placeholder="010-1234-5678"
          {...register('phone', { required: '연락처를 입력해 주세요.' })}
        ></input>
        <h3></h3>월수입
        <input
          type="text"
          placeholder=" ○○○만원"
          {...register('income', { required: '월수입을 입력해 주세요.' })}
        ></input>
        <h3>거주형태</h3>
        <input
          type="text"
          placeholder="원룸, 투룸, 전원주택 등"
          {...register('residenceType', {
            required: '거주형태를 입력해 주세요.',
          })}
        ></input>
        <h3>동거인 수</h3>
        <input type="text" placeholder="3명"></input>
        <div>
          <div>
            <h3>동거 반려동물</h3>
            <input type="text" placeholder="고양이 2마리, 강아지 1마리"></input>
          </div>
          <div>
            <h3>키워본 경험</h3>
            <input type="text" placeholder="있다/없다"></input>
          </div>
        </div>
        <h3>입양 사유</h3>
        <textarea placeholder="입양 사유를 입력해주세요."></textarea>
        <h3>거주지 사진</h3>
        <input type="file" placeholder="image."></input>
        <button>입양 신청하기</button>
      </form>
    </div>
  );
};

export default Adoption;
