import axios from 'axios';
import type { NextPage } from 'next';
import { useRouter } from 'next/router';
import { useState } from 'react';
import { SubmitHandler, useForm } from 'react-hook-form';

type AnimalType = 'CAT' | 'DOG';
type Region = 'BUSAN' | 'DAEGU' | 'GWANGJU' | 'INCHEON' | 'SEOUL';
type Sex = 'MALE' | 'FEMALE';

interface PostInput {
  animalType: AnimalType;
  categoryId: number;
  content: string;
  dueDate: string; // 'yyyy-MM-dd HH:mm:ss'
  memberId: number;
  price: number;
  region: Region;
  sex: Sex;
  title: string;
}

const Post: NextPage = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    getValues,
  } = useForm<PostInput>({
    mode: 'all',
    defaultValues: {
      title: 'test',
      categoryId: 1,
      animalType: 'CAT',
      content: 'hello',
      memberId: 2,
      price: 0,
      region: 'BUSAN',
      sex: 'FEMALE',
      dueDate: '2021-11-11 11:11:11',
    },
  });

  const router = useRouter();
  const [error, setError] = useState<boolean>(false);

  const onSubmit: SubmitHandler<PostInput> = async (formData) => {
    console.log({ formData });
    try {
      const { data } = await axios.post<PostInput, any>('/api/board', {
        ...formData,
      });

      console.log({ data });
    } catch (e) {
      setError(true);
      console.error(e);
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit(onSubmit)}>
        <input type="file" placeholder="image" />
        <input
          type="text"
          placeholder="title"
          {...register('title', { required: true })}
        />
        <input type="date" placeholder="date" {...register('dueDate')} />
        <label>나이</label>
        <input type="text" placeholder="O년 O개월" />
        <label>지역</label>
        <input type="text" placeholder="" {...register('region')} />
        <label>성별</label>
        <input type="text" placeholder="" {...register('sex')} />
        <label>가격</label>
        <input type="number" placeholder="" {...register('price')} />
        <label>중성화여부</label>
        <input type="text" placeholder="" />
        <label>종류</label>
        <input type="text" placeholder="" {...register('animalType')} />
        <label>품종</label>
        <input type="text" placeholder="" />

        <textarea {...register('content')} />

        <button>글 작성하기</button>
      </form>
    </div>
  );
};

export default Post;
