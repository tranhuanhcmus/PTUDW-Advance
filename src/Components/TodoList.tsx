import { useEffect, useState } from "react";
import TodoItem, { TodoItemProps } from "./TodoItem";
import "./styles.scss";
import TodoForm from "./TodoForm";

type Props = {};

const tasks: Array<TodoItemProps> = [
  {
    title: `Task1`,
    check: false,
    content: `This is task 1`,
  },
  {
    title: `Task2`,
    check: true,
    content: `This is task 2`,
  },
  {
    title: `Task3`,
    check: false,
    content: `This is task 3`,
  },
];

const TodoList = (props: Props) => {
  const [showModal, setShowModal] = useState(false);
  const [tasksList, setTaskList] = useState<Array<TodoItemProps>>([]);
  const [showModalEdit, setShowModalEdit] = useState(false);
  const [content, setContent] = useState("");
  const [title, setTitle] = useState("");
  const [keyedit, setKeyEdit] = useState(-1);

  const handleClick = () => {
    setShowModal(!showModal);
  };

  const handleEdit = (key: number) => {
    setShowModalEdit(!showModalEdit);
    tasksList.forEach((task, index) => {
      if (index === key) {
        setContent(task.content);
        setTitle(task.title);
        console.log(task.content, task.title);
      }
    });
    setKeyEdit(key);
  };

  useEffect(() => {
    const storedItems = localStorage.getItem("items");
    if (storedItems) {
      const items = JSON.parse(storedItems);
      setTaskList(items);
    }
  }, []);

  const handleSubmit = (title: string, content: string) => {
    const task: TodoItemProps = {
      title: title,
      check: false,
      content: content,
    };
    setShowModal(false);
    setTaskList([...tasksList, task]);
  };

  const handleSubmitEdit = (title: string, content: string) => {
    setShowModalEdit(false);
    const newTasks = tasksList.map((task, index) => {
      if (index === keyedit) {
        return {
          ...task,
          title: title,
          content: content,
        };
      }
      return task;
    });
    setTitle("");
    setContent("");
    setTaskList(newTasks);
  };

  const handleDelete = (key: any) => {
    console.log(key);
    const newTasks = tasksList.filter((task, index) => index != key);
    setTaskList((prev) => {
      return newTasks;
    });
  };
  useEffect(() => {
    setTimeout(() => {
      localStorage.setItem("items", JSON.stringify(tasksList));
    }, 0);
  }, [tasksList]);

  return (
    <>
      <div>
        <h1 className="uppercase">Todo List</h1>
        <div>
          <button onClick={handleClick}>ADD</button>
        </div>
      </div>
      <div className="w-[1024px] rounded-md overflow-hidden">
        {tasksList.map((task, index) => (
          <TodoItem
            key={index}
            {...task}
            handleDelete={() => handleDelete(index)}
            handleEdit={() => handleEdit(index)}
          />
        ))}
      </div>
      <TodoForm
        isShow={showModal}
        handleClick={handleClick}
        handleSubmit={handleSubmit}
      />
      <TodoForm
        isShow={showModalEdit}
        handleClick={() => {
          setShowModalEdit(!showModalEdit);
        }}
        handleSubmit={handleSubmitEdit}
        content={content}
        title={title}
      />
    </>
  );
};

export default TodoList;
