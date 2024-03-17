import { useEffect, useRef, useState } from 'react';
import './Task.scss';

export function Task() {
  const inputRef = useRef<HTMLInputElement | null>(null);
  const mutableValueRef = useRef<number>(0);
  const [time, setTime] = useState<number>(0);
  const timeRef = useRef<number | undefined>();
  const buttonRef = useRef<HTMLDivElement | null>(null);

  useEffect(() => {
    inputRef.current?.focus();
  }, []);

  const startTimer = () => {
    timeRef.current = setInterval(() => {
      mutableValueRef.current += 1;
      console.log("Mutable Value:", mutableValueRef.current);

      setTime((prevTime) => prevTime + 1);
    }, 1000);
  };

  const endTimer = () => {
    clearInterval(timeRef.current);
  };

  const resetTimer = () => {
    clearInterval(timeRef.current);
    setTime(0);
  };

  const scrollToTop = () => {
    if (buttonRef.current) {
      window.scrollTo({
        top: buttonRef.current.offsetTop,
        left: 0,
        behavior: 'smooth',
      });
    }
  };

  return (
    <div className='main-container'>
      <div ref={buttonRef}>
        <p>Top position</p>
      </div>
      <hr />
      <div className='timer-container'>
        <p className='time-text'>Timer: {time} seconds</p>
        <button className='time-button' onClick={startTimer}>
          Start Timer
        </button>
        <button className='time-button' onClick={endTimer}>
          End Timer
        </button>
        <button className='time-button' onClick={resetTimer}>
          Reset Timer
        </button>
      </div>
      <div className='input-box-container'>
        <input className='input-box' type='text' ref={inputRef} placeholder='Input 1' />
        <input className='input-box' type='text' placeholder='Input 2' />
      </div>
      <button className='scroll-top-button' onClick={scrollToTop}>
        Scroll to Top
      </button>
    </div>
  );
}
