import type { NextPage, NextPageContext } from 'next';
import { SubmitHandler, useForm } from 'react-hook-form';
import styles from './signin.module.css';
import axios from 'axios';
import { useRouter } from 'next/router';
import { useState } from 'react';
import { KakaoOauth, KakaoUser } from 'types/kakao';

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
    setValue,
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
        router.push('/');
      }
    } catch (e) {
      setError(true);
    }
  };

  function handleSignUpCllick() {
    router.push('/signup');
  }

  function handleKakaoClick() {
    Kakao.Auth.login({
      success: function (authObj: KakaoOauth) {
        console.log({ authObj });
        Kakao.API.request({
          url: '/v2/user/me',
          success: function ({
            id,
            kakao_account: { email },
            properties: { nickname },
          }: KakaoUser) {
            onSubmit({ email, password: id.toString() });
          },
          fail: function (error: string) {
            console.log(error);
          },
        });
      },
      fail: function (err: string) {
        alert(JSON.stringify(err));
      },
    });
  }

  return (
    <div>
      <h1>로그인</h1>
      <img
        className={styles.snsButton}
        src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"
        width="222"
        onClick={handleKakaoClick}
      />
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
