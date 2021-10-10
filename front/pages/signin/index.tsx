import type { NextPage } from 'next';
import { SubmitHandler, useForm } from 'react-hook-form';
import styles from './signin.module.css';
import axios from 'axios';
import { useRouter } from 'next/router';
import { useState } from 'react';

interface SignInInput {
  email: string;
  password: string;
}

interface SignInResponse {
  data: string;
}

const SignIn: NextPage = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    getValues,
  } = useForm<SignInInput>({ mode: 'all' });

  const router = useRouter();

  const [error, setError] = useState<boolean>(false);

  const onSubmit: SubmitHandler<SignInInput> = async (formData) => {
    try {
      const { data } = await axios.post<SignInInput, SignInResponse>(
        '/api/session/login',
        { ...formData }
      );
      if (data === 'ACCEPTED') {
        document.cookie = 'login=true';
        router.push('/');
      }
    } catch (e) {
      setError(true);
    }
  };

  function handleSignUpCllick() {
    router.push('/signup');
  }

  return (
    <div>
      <h1>로그인</h1>
      <button>카카오로 계속하기</button>
      <button>Facebook으로 계속하기</button>
      <form onSubmit={handleSubmit(onSubmit)}>
        <input
          type="email"
          aria-label="email"
          placeholder="email"
          autoComplete="username"
          {...register('email', { required: true })}
        />
        {errors.email && <p>이메일을 입력해 주세요.</p>}
        <input
          type="password"
          aria-label="password"
          placeholder="비밀번호"
          autoComplete="password"
          {...register('password', { required: true })}
        />
        {errors.password && <p>비밀번호를 입력해 주세요.</p>}
        <button>로그인하기</button>
      </form>
      {error && <p>로그인에 실패했습니다.</p>}
      <button onClick={handleSignUpCllick}>{'회원가입 >'}</button>
    </div>
  );
};

export default SignIn;
