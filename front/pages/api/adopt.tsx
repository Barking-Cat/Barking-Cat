import axios from 'axios';

// interface 선언해서 다시 수정

export const postAdopts = async ({ adoptForm }: any) => {
  const { response } = await axios.post(`/api/adopt`, {
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      adoptReason: 'soso',
      area: 'seoul',
      boardId: 0,
      earning: 'FIVE_HUNDRED',
      petCount: 1,
      region: 'BUSAN',
      residenceType: 'LEASE',
      roommateNumber: 1,
    }),
  });
  console.log(response);
  if (response.status !== 201) {
    throw new Error('Error !');
  }
  return response;
};

export const getAdoptById = async (boardId: Number) => {
  const response = await axios.put(`/api/adopt/${boardId}`, {
    headers: {
      'Content-Type': 'application/json',
    },
  });
  console.log(response);
  return response;
};
