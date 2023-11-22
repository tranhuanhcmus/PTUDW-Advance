import React from 'react';
import ChatRoom from './component/ChatRoom';
export default function App() {
  // const [isConnected, setIsConnected] = useState();
  // const [fooEvents, setFooEvents] = useState([]);

  // useEffect(() => {
  //   const socket = new SockJS("http://localhost:8080/chat");
  //   const stompClient = Stomp.over(socket);

  //   stompClient.connect({}, function (frame) {
  //     setIsConnected(true);
  //        stompClient.subscribe("/topic/public", function (message) {
  //        console.log("🚀 ~ file: App.js:17 ~ message:", message)

  //        });
  //        stompClient.send("/app/chat.addUser",
  //           {},
  //           JSON.stringify({sender: 'new mem23334444', type: 'JOIN'})
  //         )
  //     });

  //   // function onConnected() {
  //   //   setIsConnected(true);
  //   //   setTimeout(() => {
  //   //       stompClient.subscribe('/topic/foo', onMessageReceived);
  //   //       stompClient.send("/app/chat.addUser",
  //   //         {},
  //   //         JSON.stringify({sender: 'new mem2', type: 'JOIN'})
  //   //       )
  //   //   }, 1000);
  //   // }

  //   // function onMessageReceived(payload) {
  //   //   const message = JSON.parse(payload.body);
  //   //   console.log("🚀 ~ file: App.js:27 ~ onMessageReceived ~ message:", message)
  //   //   setFooEvents(fooEvents => [...fooEvents, message]);
  //   // }

  //   // function onError(error) {
  //   //   setIsConnected(false);
  //   //   console.log(error);
  //   // }


  //   // return () => {
  //   //   stompClient.unsubscribe('/topic/foo');
  //   //   stompClient.disconnect();
      
      
  //   // };
  // }, []);

  return (
    <div className="App">
      <ChatRoom />
    </div>
  );
}