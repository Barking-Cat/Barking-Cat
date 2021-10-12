import { NextPageContext } from 'next';
import cookie from 'cookie';
import axios from 'axios';

const cookieKeys = {
  loginToken: 'barking.cat',
} as const;

export function getCookie(key: keyof typeof cookieKeys, ctx: NextPageContext) {
  return cookie.parse(ctx.req?.headers.cookie ?? '')[cookieKeys[key]] ?? '';
}

export function deleteCookie(key: string) {
  document.cookie = `${key}=;expires=Thu, 01 Jan 1970 00:00:01 GMT`;
}

export const apiServer = axios.create({
  baseURL: process.env.NEXT_PUBLIC_SERVER_API,
});
