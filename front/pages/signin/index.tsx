import type { NextPage } from 'next';
import { SubmitHandler, useForm } from 'react-hook-form';
import styles from './signin.module.css';

interface SignInInput {
  email: string;
  password: string;
}

const SignIn: NextPage = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    getValues,
  } = useForm<SignInInput>({ mode: 'all' });

  const onSubmit: SubmitHandler<SignInInput> = (data) => console.log({ data });

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
        <input
          type="password"
          aria-label="password"
          placeholder="비밀번호"
          autoComplete="password"
          {...register('password', { required: true })}
        />
        <button>로그인하기</button>
      </form>
    </div>
  );
};

export default SignIn;
