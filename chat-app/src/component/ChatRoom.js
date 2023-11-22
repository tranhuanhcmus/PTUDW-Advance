import React, { useEffect, useState } from 'react'
import {over} from 'stompjs';
import SockJS from 'sockjs-client';

var stompClient =null;
const ChatRoom = () => { 
    const [publicChats, setPublicChats] = useState([]); 
    const [tab,setTab] =useState("CHATROOM");
    const [userData, setUserData] = useState({
        username: '',
        receivername: '',
        connected: false,
        content: ''
      });
    useEffect(() => {
      console.log('userData', userData);
    }, [userData]);

    const connect =()=>{
        let Sock = new SockJS('http://localhost:8080/chat');
        stompClient = over(Sock);
        stompClient.connect({},onConnected, onError);
    }

    const onConnected = () => {
        setUserData({...userData,"connected": true});
        stompClient.subscribe('/topic/public', onMessageReceived);
        userJoin();
    }

    const userJoin=()=>{
          var chatMessage = {
            sender: userData.username,
            type:"JOIN"
          };
          stompClient.send("/app/chat.addUser", {}, JSON.stringify(chatMessage));
    }

    const onMessageReceived = (payload)=>{
        var payloadData = JSON.parse(payload.body);
        console.log("🚀 ~ file: ChatRoom.js:41 ~ onMessageReceived ~ payloadData:", payloadData)
        switch(payloadData.type){
            case "JOIN":
                publicChats.push({
                    sender: payloadData.sender,
                    content: payloadData.sender + " joined!",
                    type:"JOIN"
                });
                setPublicChats([...publicChats]);
                break;
            case "CHAT":
                publicChats.push(payloadData);
                setPublicChats([...publicChats]);
                break;
        }
    }
    const onError = (err) => {
        console.log(err);
    }

    const handleMessage =(event)=>{
        const {value}=event.target;
        setUserData({...userData,"content": value});
    }
    const sendValue=()=>{

            var chatMessage = {
            sender: userData.username,
            content: userData.content,
            type:"CHAT"
            };
            stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
            setUserData({...userData,"content": ""});
    }

    const handleUsername=(event)=>{
        const {value}=event.target;
        setUserData({...userData,"username": value});
    }

    const registerUser=()=>{
        connect();
    }
    return (
        <div className="container">
            {userData.connected?
            <div className="chat-box">
                <div className="member-list">
                    <ul>
                        <li onClick={()=>{setTab("CHATROOM")}} className={`member ${tab==="CHATROOM" && "active"}`}>Chatroom</li>
                    </ul>
                </div>
                {tab==="CHATROOM" && <div className="chat-content">
                    <ul className="chat-messages">
                        {publicChats.map((chat,index)=>(
                            <li className={`message ${chat.sender === userData.username && "self"}`} key={index}>
                                {chat.sender !== userData.username && <div className="avatar">{chat.sender}</div>}
                                <div className="message-data">{chat.content}</div>
                                {chat.sender === userData.username && <div className="avatar self">{chat.sender}</div>}
                            </li>
                        ))}
                    </ul>
    
                    <div className="send-message">
                        <input type="text" className="input-message" placeholder="enter the message" value={userData.content} onChange={handleMessage} /> 
                        <button type="button" className="send-button" onClick={sendValue}>send</button>
                    </div>
                </div>}
                {tab!=="CHATROOM" && <div className="chat-content">
                </div>}
            </div>
            :
            <div className="register">
                <input
                    id="user-name"
                    placeholder="Enter your name"
                    name="userName"
                    value={userData.username}
                    onChange={handleUsername}
                    margin="normal"
                  />
                  <button type="button" onClick={registerUser}>
                        connect
                  </button> 
            </div>}
        </div>
        )
}

export default ChatRoom