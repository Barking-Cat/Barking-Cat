import { PostList } from '@/components/board/post';
import axios from 'axios';
import Head from 'next/head';
import React, { useEffect, useState } from 'react';
import styles from '../styles/Home.module.css';
import { useRouter } from 'next/router';
import type { NextPage } from 'next';
import type { BoardResponse } from 'types/api';
import type { Post } from 'types/post';

const Home: NextPage = () => {
  const [data, setData] = useState<Post[]>([]);

  const router = useRouter();

  async function fetch() {
    const { data } = await axios.get<BoardResponse>('/api/board');
    setData(data.content);
  }

  useEffect(() => {
    fetch();
  }, []);

  function handleSignInClick() {
    router.push('/signin');
  }
  function handleSignUpClick() {
    router.push('/signup');
  }

  return (
    <div className={styles.container}>
      <Head>
        <title>야옹아 멍멍해봐</title>
        <meta name="description" content="Generated by create next app" />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main className={styles.main}>
        <button onClick={handleSignInClick}>로그인</button>
        <button onClick={handleSignUpClick}>회원가입</button>
        <PostList items={data} />
      </main>
    </div>
  );
};

export default Home;
