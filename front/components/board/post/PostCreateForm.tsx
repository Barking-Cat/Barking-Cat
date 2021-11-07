import axios from 'axios';
import moment from 'moment';
import { useRouter } from 'next/router';
import { useState } from 'react';
import { SubmitHandler, useForm } from 'react-hook-form';
import type { PostCreate } from 'types/post';

function PostCreateForm() {
  const {
    register,
    handleSubmit,
    formState: { errors },
    getValues,
  } = useForm<PostCreate>({
    mode: 'all',
    defaultValues: {
      title: 'test',
      age: 0,
      categoryId: 1,
      animalType: 'DOG',
      content: 'hello',
      price: 0,
      region: 'BUSAN',
      sex: 'FEMALE',
      dueDate: moment().add(1, 'M').format('YYYY-MM-DD HH:mm:ss'),
    },
  });

  const router = useRouter();
  const [error, setError] = useState<boolean>(false);

  const onSubmit: SubmitHandler<PostCreate> = async (formData) => {
    try {
      await axios.post<PostCreate, any>('/api/board', {
        ...formData,
      });

      router.push('/');
    } catch (e) {
      setError(true);
      console.error(e);
    }
  };
  return (
    <div>
      <form onSubmit={handleSubmit(onSubmit)}>
        <input type="file" placeholder="image" />
        <input
          type="text"
          placeholder="title"
          {...register('title', { required: true, minLength: 5 })}
        />
        <input type="date" placeholder="date" {...register('dueDate')} />
        <label>나이</label>
        <input type="number" placeholder="" {...register('age')} />
        <label>지역</label>
        <input type="text" placeholder="" {...register('region')} />
        <label>성별</label>
        <input type="text" placeholder="" {...register('sex')} />
        <label>가격</label>
        <input type="number" placeholder="" {...register('price')} />
        <label>중성화여부</label>
        <input type="text" placeholder="" />
        <label>종류</label>
        <input type="text" placeholder="" {...register('animalType')} />
        <label>품종</label>
        <input type="text" placeholder="" />

        <textarea {...register('content')} />

        <button>글 작성하기</button>
      </form>
    </div>
  );
}

export default PostCreateForm;
