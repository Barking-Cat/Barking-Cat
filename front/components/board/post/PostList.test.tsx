import { render, screen } from '@testing-library/react';
import { Post } from 'types/post';
import PostList from './PostList';

export const mockData: Post[] = [
  {
    boardId: 1,
    categoryId: 1,
    memberId: 1,
    title: 'TEST TITLE',
    content: 'TEST CONTENT',
    region: 'SEOUL',
    animalType: 'DOG',
    sex: 'MALE',
    age: 3,
    price: 50000,
    createdAt: '2021-09-01',
    dueDate: '2021-10-01',
    tags: ['tag1', 'tag2', 'tag3'],
    hits: 0,
  },
  {
    boardId: 2,
    categoryId: 1,
    memberId: 1,
    title: 'TEST TITLE',
    content: 'TEST CONTENT',
    region: 'SEOUL',
    animalType: 'DOG',
    sex: 'MALE',
    age: 3,
    price: 50000,
    createdAt: '2021-09-01',
    dueDate: '2021-10-01T00:00:00',
    tags: ['tag1', 'tag2', 'tag3'],
    hits: 0,
  },
  {
    boardId: 3,
    categoryId: 1,
    memberId: 1,
    title: 'TEST TITLE',
    content: 'TEST CONTENT',
    region: 'SEOUL',
    animalType: 'DOG',
    sex: 'MALE',
    age: 3,
    price: 50000,
    createdAt: '2021-09-01',
    dueDate: '2021-10-01T00:00:00',
    tags: ['tag1', 'tag2', 'tag3'],
    hits: 0,
  },
];

describe('PostCard List', () => {
  it('should render post list', () => {
    render(<PostList items={mockData} />);
    expect(screen.getAllByRole('listitem')).toHaveLength(mockData.length);
  });
});
