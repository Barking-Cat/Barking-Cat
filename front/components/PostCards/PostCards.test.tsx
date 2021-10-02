import { render, screen } from '@testing-library/react';
import PostCards from '.';
import { PostCardItem } from 'types/api';

const mockData: PostCardItem[] = [
  {
    id: 1,
    img: 'http://sample-image.com',
    title: '사지말고 입양하세요.',
    tags: ['3살', '서울', '여아'],
    reads: 42,
    likes: 12,
    comments: 5,
    createdDate: '2021-10-02',
    dueDate: '2021-11-02',
  },
  {
    id: 2,
    img: 'http://sample-image.com',
    title: '사지말고 입양하세요.',
    tags: ['3살', '서울', '여아'],
    reads: 42,
    likes: 12,
    comments: 5,
    createdDate: '2021-10-02',
    dueDate: '2021-11-02',
  },
  {
    id: 3,
    img: 'http://sample-image.com',
    title: '사지말고 입양하세요.',
    tags: ['3살', '서울', '여아'],
    reads: 42,
    likes: 12,
    comments: 5,
    createdDate: '2021-10-02',
    dueDate: '2021-11-02',
  },
];

describe('PostCard List', () => {
  it('should render post list', () => {
    render(<PostCards items={mockData} />);
    expect(screen.getAllByRole('listitem')).toHaveLength(mockData.length);
  });
});
