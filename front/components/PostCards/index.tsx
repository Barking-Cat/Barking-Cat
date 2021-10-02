import PostCard from '@/components/PostCard';
import { PostCardItem } from 'types/api';

interface PostCardsProps {
  items: PostCardItem[];
}
function PostCards({ items }: PostCardsProps) {
  return (
    <ul>
      {items.map((item) => (
        <PostCard key={item.id} {...item} />
      ))}
    </ul>
  );
}

export default PostCards;
