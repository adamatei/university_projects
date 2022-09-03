export const countUsers = (users, role) => {
  let count = 0;

  users.forEach((user) => {
    if (user.role === role) {
      count++;
    }
  });
  return count;
};

export const getPercentage = (amount, fullAmount) => {
  if (fullAmount === 0) return 0;
  return ((amount * 100) / fullAmount).toFixed(1);
};
