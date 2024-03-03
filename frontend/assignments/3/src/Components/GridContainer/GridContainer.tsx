import { Cell, Container } from "./GridComponentStyles";

const GridContainer = () => {
  const cells = Array.from({ length: 6 * 5 }, (_, index) => (
    <Cell key={index}> </Cell>
  ));

  return <Container>{cells}</Container>;
};

export default GridContainer;
