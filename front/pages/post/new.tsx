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

Post.getInitialProps = (ctx: NextPageContext) => {
  const isLogin = getCookie('login', ctx);

  return { isLogin: !!isLogin };
};

export default Post;
