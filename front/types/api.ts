export interface BoardResponse {
  content: [
    {
      boardId: number;
      categoryId: number;
      memberId: number;
      title: string;
      content: string;
      region: string;
      animalType: string;
      sex: string;
      age: 3;
      price: 50000;
      dueDate: string;
      tags: string[];
    }
  ];
  pageable: {
    sort: {
      sorted: boolean;
      unsorted: boolean;
      empty: boolean;
    };
    offset: number;
    pageNumber: number;
    pageSize: number;
    paged: boolean;
    unpaged: boolean;
  };
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  number: number;
  sort: {
    sorted: boolean;
    unsorted: boolean;
    empty: boolean;
  };
  numberOfElements: number;
  first: boolean;
  empty: boolean;
}

export interface PostCardItem {
  id: number;
  img: string;
  title: string;
  tags: string[];
  reads: number;
  likes: number;
  comments: number;
  createdDate: string;
  dueDate: string;
}
