import { PostCreateForm } from '@/components/board/post';
import type { NextPage, NextPageContext } from 'next';
import router from 'next/router';
import { useEffect } from 'react';
import { getCookie } from 'utils';

interface PostProps {
  isLogin: boolean;
}

const Post: NextPage<PostProps> = ({ isLogin }) => {
  useEffect(() => {
    if (!isLogin) {
      router.push('/signin');
    }
  }, [isLogin]);
  return <PostCreateForm />;
};

export async function getServerSideProps(ctx: NextPageContext) {
  const isLogin = getCookie('loginToken', ctx);
  return { props: { isLogin: !!isLogin } };
}

export default Post;
