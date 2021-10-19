export interface KakaoOauth {
  access_token: string;
  token_type: string;
  refresh_token: string;
  expires_in: number;
  scope: string;
  refresh_token_expires_in: number;
}

export interface KakaoUser {
  connected_at: string;
  id: number;
  kakao_account: {
    email: string;
    has_email: boolean;
  };
  properties: {
    nickname: string;
  };
}