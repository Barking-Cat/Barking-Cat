declare module Kakao {
  module Auth {
    function login(params: LoginProps): void;
  }

  module API {
    function request(params: RequestProps): void;
  }
}

interface LoginProps {
  success?: (authObj: LoginAuth) => void;
  fail?: (error: string) => void;
}

interface RequestProps {
  url: string;
  success?: (userObj: User) => void;
  fail?: (error: string) => void;
}

interface User {
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

interface LoginAuth {
  access_token: string;
  token_type: string;
  refresh_token: string;
  expires_in: number;
  scope: string;
  refresh_token_expires_in: number;
}
