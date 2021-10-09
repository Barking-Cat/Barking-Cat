import { render, screen } from '@testing-library/react';
import PostItem from './PostItem';
import { mockData } from './PostList.test';

const data = mockData[0];

describe('PostCard', () => {
  it('should render pet image', () => {
    render(<PostItem {...data} />);
    expect(screen.getByRole('img', { name: 'pet-image' })).toBeInTheDocument();
  });

  it('should render post title', () => {
    render(<PostItem {...data} />);
    expect(screen.getByRole('heading')).toBeInTheDocument();
  });

  it('should render post tags', () => {
    render(<PostItem {...data} />);
    expect(screen.getAllByRole('button')).toHaveLength(data.tags.length);
  });

  it('should render post dates', () => {
    render(<PostItem {...data} />);
    expect(
      screen.getByText(`${data.createdAt} ~ ${data.dueDate}`)
    ).toBeInTheDocument();
  });

  it('should render post numeric info', () => {
    render(<PostItem {...data} />);
    expect(screen.getByLabelText('hits')).toHaveTextContent(
      data.hits.toString()
    );
    expect(screen.getByLabelText('likes')).toHaveTextContent(
      data.likes?.toString() ?? '0'
    );
    expect(screen.getByLabelText('comments')).toHaveTextContent(
      data.comments?.toString() ?? '0'
    );
  });
});
