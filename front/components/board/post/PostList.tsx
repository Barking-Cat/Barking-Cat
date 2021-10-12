import PostItem from './PostItem';
import type { Post } from 'types/post';
import styles from './PostList.module.css';

interface PostListProps {
  items: Post[];
}

function PostList({ items }: PostListProps) {
  return (
    <ul className={styles.container}>
      {items.map((item) => (
        <PostItem key={item.boardId} {...item} />
      ))}
    </ul>
  );
}

export default PostList;
