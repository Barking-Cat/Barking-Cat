import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import SignUp from '../pages/signup';

describe('Signup Page', () => {
  it('should render signup header', () => {
    render(<SignUp />);

    const headerEl = screen.getByRole('heading', { name: /회원가입/g });
    expect(headerEl).toBeInTheDocument();
  });

  it('should render kakao button', () => {
    render(<SignUp />);

    const buttonEl = screen.getByRole('button', { name: /카카오로 계속하기/g });
    expect(buttonEl).toBeInTheDocument();
  });

  it('should render facebook button', () => {
    render(<SignUp />);

    const buttonEl = screen.getByRole('button', {
      name: /Facebook으로 계속하기/g,
    });
    expect(buttonEl).toBeInTheDocument();
  });

  it('should render name input field', () => {
    render(<SignUp />);

    const nameInputEl = screen.getByRole('textbox', { name: /name/i });
    expect(nameInputEl).toBeInTheDocument();
  });

  it('should render email input field', () => {
    render(<SignUp />);

    const emailInputEl = screen.getByRole('textbox', { name: /email/i });
    expect(emailInputEl).toBeInTheDocument();
  });

  it('should render password input field', () => {
    render(<SignUp />);

    const passwordInputEl = screen.getByPlaceholderText('비밀번호');
    expect(passwordInputEl).toBeInTheDocument();
  });

  it('should render password confirm input field', () => {
    render(<SignUp />);

    const passwordRepeatInputEl = screen.getByPlaceholderText('비밀번호 확인');
    expect(passwordRepeatInputEl).toBeInTheDocument();
  });

  it('should render phone number input field', () => {
    render(<SignUp />);

    const mobileNumberInputEl = screen.getByRole('textbox', {
      name: /phone/i,
    });
    expect(mobileNumberInputEl).toBeInTheDocument();
  });

  it('should render submit button', () => {
    render(<SignUp />);

    const buttonEl = screen.getByRole('button', {
      name: /계정 만들기/g,
    });
    expect(buttonEl).toBeInTheDocument();
  });
});

describe('Email validation', () => {
  it('should render error message when email is invalid', async () => {
    render(<SignUp />);

    userEvent.type(screen.getByRole('textbox', { name: /email/i }), 'abc.com');
    await waitFor(() =>
      expect(
        screen.queryByText('유효하지 않은 이메일입니다.')
      ).toBeInTheDocument()
    );
  });

  it('should not render error message when email is valid', async () => {
    render(<SignUp />);

    userEvent.type(
      screen.getByRole('textbox', { name: /email/i }),
      'test@test.com'
    );
    await waitFor(() =>
      expect(
        screen.queryByText('유효하지 않은 이메일입니다.')
      ).not.toBeInTheDocument()
    );
  });
});

describe('Password validation', () => {
  it('should warn when passwords are not the same', async () => {
    render(<SignUp />);

    userEvent.type(screen.getByPlaceholderText('비밀번호'), 'pass');

    userEvent.type(screen.getByPlaceholderText('비밀번호 확인'), 'passpass');

    await waitFor(() => {
      expect(
        screen.queryByText('비밀번호가 일치하지 않습니다.')
      ).toBeInTheDocument();
    });
  });

  it('should not warn when passwords are the same', async () => {
    render(<SignUp />);

    userEvent.type(screen.getByPlaceholderText('비밀번호'), 'pass');

    userEvent.type(screen.getByPlaceholderText('비밀번호 확인'), 'pass');

    await waitFor(() => {
      expect(
        screen.queryByText('비밀번호가 일치하지 않습니다.')
      ).not.toBeInTheDocument();
    });
  });
});
