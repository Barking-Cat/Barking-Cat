import { render, screen } from '@testing-library/react';
import SignIn from '../pages/signin/index';

describe('SignIn Page', () => {
  it('should render header', () => {
    render(<SignIn />);
    expect(screen.getByRole('heading', { name: '로그인' })).toBeInTheDocument();
  });

  it('should render kakao button', () => {
    render(<SignIn />);
    expect(screen.getByRole('img', { name: /kakao/i })).toBeInTheDocument();
  });

  it('should render facebook button', () => {
    render(<SignIn />);
    expect(
      screen.getByRole('button', { name: /facebook/i })
    ).toBeInTheDocument();
  });

  it('should render email input', () => {
    render(<SignIn />);
    expect(screen.getByRole('textbox', { name: 'email' })).toBeInTheDocument();
  });

  it('should render password input', () => {
    render(<SignIn />);
    expect(screen.getByPlaceholderText('비밀번호')).toBeInTheDocument();
  });

  it('should render signIn button', () => {
    render(<SignIn />);
    expect(
      screen.getByRole('button', { name: '로그인하기' })
    ).toBeInTheDocument();
  });
});
