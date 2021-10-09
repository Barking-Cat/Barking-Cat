import PostItem from './PostItem';
import type { Post } from 'types/post';

interface PostListProps {
  items: Post[];
}
function PostList({ items }: PostListProps) {
  return (
    <ul>
      {items.map((item) => (
        <PostItem key={item.boardId} {...item} />
      ))}
    </ul>
  );
}

export default PostList;
