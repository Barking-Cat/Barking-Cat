import { PostElement } from 'types/api';

interface PostItemProps extends PostElement {}

function PostItem({
  img,
  title,
  tags,
  reads,
  likes,
  comments,
  createdDate,
  dueDate,
}: PostItemProps) {
  return (
    <li>
      <img src={img} alt="pet-image" />
      <h3>{title}</h3>
      {tags.map((tag) => (
        <button key={tag}>{tag}</button>
      ))}
      <p>{`${createdDate} ~ ${dueDate}`}</p>
      <span aria-label="reads">{reads}</span>
      <span aria-label="likes">{likes}</span>
      <span aria-label="comments">{comments}</span>
    </li>
  );
}

export default PostItem;
