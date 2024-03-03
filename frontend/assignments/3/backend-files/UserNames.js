const userName = [
  "Manav",
  "Sagun",
  "Aakash",
  "Nitesh",
  "Anupam",
  "Rishav",
  "Rohit",
];
function getRandomName() {
  const randomIndex = Math.floor(Math.random() * userName.length);
  return userName[randomIndex];
}
module.exports = { getRandomName };
