import { NextPageContext } from 'next';
import cookie from 'cookie';

export function getCookie(key: string, ctx?: NextPageContext) {
  return cookie.parse(
    ctx && ctx.req ? ctx.req.headers.cookie ?? '' : document.cookie
  )[key];
}

export function deleteCookie(key: string) {
  document.cookie = `${key}=;expires=Thu, 01 Jan 1970 00:00:01 GMT`;
}
