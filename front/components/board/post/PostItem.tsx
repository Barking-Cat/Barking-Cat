import moment from 'moment';
import { Post } from 'types/post';

interface PostItemProps extends Post {
  likes?: number;
  comments?: number;
}

function PostItem({
  title,
  tags,
  hits,
  dueDate,
  likes,
  comments,
  createdAt,
  imgs,
}: PostItemProps) {
  return (
    <li>
      <img alt="pet-image" />
      <h3>{title}</h3>
      {tags && tags.map((tag) => <button key={tag}>{tag}</button>)}
      <p>{`${createdAt ?? moment().format('YYYY-MM-DD')} ~ ${dueDate}`}</p>
      <span aria-label="hits">{hits}</span>
      <span aria-label="likes">{likes ?? 0}</span>
      <span aria-label="comments">{comments ?? 0}</span>
    </li>
  );
}

export default PostItem;
