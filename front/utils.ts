import { NextPageContext } from 'next';
import cookie from 'cookie';

const CookieKeys = {
  loginToken: 'barking.cat',
} as const;

export function getCookie(key: keyof typeof CookieKeys, ctx: NextPageContext) {
  return cookie.parse(ctx.req?.headers.cookie ?? '')[CookieKeys[key]] ?? '';
}

export function deleteCookie(key: string) {
  document.cookie = `${key}=;expires=Thu, 01 Jan 1970 00:00:01 GMT`;
}
