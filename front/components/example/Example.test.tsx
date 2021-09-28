import { render, screen } from '@testing-library/react';
import Example from '.';

describe('Example', () => {
  it('should render Title', () => {
    render(<Example />);

    const titleEl = screen.getByRole('heading', { name: 'Example' });
    expect(titleEl).toBeInTheDocument();
  });
});
