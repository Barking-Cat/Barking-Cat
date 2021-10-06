import { render, screen } from '@testing-library/react';
import type { PostElement } from 'types/api';
import PostItem from './PostItem';

const mockData: PostElement = {
  id: 1,
  img: 'http://sample-image.com',
  title: '사지말고 입양하세요.',
  tags: ['3살', '서울', '여아'],
  reads: 42,
  likes: 12,
  comments: 5,
  createdDate: '2021-10-02',
  dueDate: '2021-11-02',
};

describe('PostCard', () => {
  it('should render pet image', () => {
    render(<PostItem {...mockData} />);
    expect(screen.getByRole('img', { name: 'pet-image' })).toBeInTheDocument();
  });

  it('should render post title', () => {
    render(<PostItem {...mockData} />);
    expect(screen.getByRole('heading')).toBeInTheDocument();
  });

  it('should render post tags', () => {
    render(<PostItem {...mockData} />);
    expect(screen.getAllByRole('button')).toHaveLength(mockData.tags.length);
  });

  it('should render post dates', () => {
    render(<PostItem {...mockData} />);
    expect(
      screen.getByText(`${mockData.createdDate} ~ ${mockData.dueDate}`)
    ).toBeInTheDocument();
  });

  it('should render post numeric info', () => {
    render(<PostItem {...mockData} />);
    expect(screen.getByLabelText('reads')).toHaveTextContent(
      mockData.reads.toString()
    );
    expect(screen.getByLabelText('likes')).toHaveTextContent(
      mockData.likes.toString()
    );
    expect(screen.getByLabelText('comments')).toHaveTextContent(
      mockData.comments.toString()
    );
  });
});
