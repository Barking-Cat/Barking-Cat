import PostItem from './PostItem';
import { PostElement } from 'types/api';

interface PostListProps {
  items: PostElement[];
}
function PostList({ items }: PostListProps) {
  return (
    <ul>
      {items.map((item) => (
        <PostItem key={item.id} {...item} />
      ))}
    </ul>
  );
}

export default PostList;
