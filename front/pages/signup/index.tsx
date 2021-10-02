import type { NextPage } from 'next';
import React, { useState } from 'react';
import styles from './signup.module.css';
import { SubmitHandler, useForm } from 'react-hook-form';
import axios from 'axios';
import { useRouter } from 'next/router';

const EMAIL_PATTERN =
  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

interface SignUpInput {
  name: string;
  email: string;
  password: string;
  passwordRepeat: string;
  phone: string;
}

interface SignUpRequest extends SignUpInput {
  authState: boolean;
}

interface SignUpResponse {
  data: string;
}

const SignUp: NextPage = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    getValues,
  } = useForm<SignUpInput>({ mode: 'all' });

  const router = useRouter();
  const [error, setError] = useState<boolean>(false);

  const onSubmit: SubmitHandler<SignUpInput> = async (formData) => {
    try {
      const { data } = await axios.post<SignUpRequest, SignUpResponse>(
        '/api/member/join',
        {
          ...formData,
          authState: true,
        }
      );

      if (data === 'CREATED') {
        router.push('/signin');
      }
    } catch (e) {
      setError(true);
    }
  };

  return (
    <div>
      <h1>회원가입</h1>
      <button>카카오로 계속하기</button>
      <button>Facebook으로 계속하기</button>
      <form className={styles.form} onSubmit={handleSubmit(onSubmit)}>
        <input
          aria-label="name"
          type="username"
          placeholder="이름"
          {...register('name', { required: '이름을 입력해 주세요.' })}
        />
        {errors.name && <p>{errors.name.message}</p>}
        <input
          aria-label="email"
          type="email"
          autoComplete="username"
          placeholder="이메일"
          {...register('email', {
            required: '이메일을 입력해 주세요.',
            pattern: {
              value: EMAIL_PATTERN,
              message: '유효하지 않은 이메일입니다.',
            },
          })}
        />
        {errors.email && <p>{errors.email.message}</p>}
        <input
          aria-label="password"
          type="password"
          autoComplete="new-password"
          placeholder="비밀번호"
          {...register('password', { required: '비밀번호를 입력해 주세요.' })}
        />
        {errors.password && <p>{errors.password.message}</p>}
        <input
          aria-label="password-repeat"
          type="password"
          autoComplete="new-password"
          placeholder="비밀번호 확인"
          {...register('passwordRepeat', {
            required: '비밀번호를 다시 입력해 주세요.',
            validate: (value) =>
              value === getValues().password || '비밀번호가 일치하지 않습니다.',
          })}
        />
        {errors.passwordRepeat && <p>{errors.passwordRepeat.message}</p>}
        <input
          aria-label="phone"
          type="text"
          placeholder="핸드폰 번호"
          {...register('phone', { required: '핸드폰 번호를 입력해 주세요.' })}
        />
        {errors.phone && <p>{errors.phone.message}</p>}
        <button>계정 만들기</button>
      </form>
      {error && <p>계정 만들기에 실패했습니다.</p>}
    </div>
  );
};

export default SignUp;
