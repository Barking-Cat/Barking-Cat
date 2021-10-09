export type AnimalType = 'CAT' | 'DOG';
export type Region = 'BUSAN' | 'DAEGU' | 'GWANGJU' | 'INCHEON' | 'SEOUL';
export type Sex = 'MALE' | 'FEMALE';

export interface PostBase {
  title: string;
  content: string;
  imgs?: string[];
  categoryId: number;
  age?: number;
  animalType: AnimalType;
  price: number;
  region: Region;
  sex: Sex;
  dueDate: string;
  tags?: string[];
}

export interface PostCreate extends PostBase {}

export interface Post extends PostBase {
  boardId: number;
  memberId: number;
  // createdAt: Date;
  hits: number;
  likes?: number;
  comments?: number;
  createdAt: string;
  tags: string[];
}
