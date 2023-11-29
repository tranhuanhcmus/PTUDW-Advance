import { useState, useEffect } from "react";

export type TodoItemProps = {
  title: string;
  content: string;
  check: boolean;
  handleDelete?: (e: any) => void;
  handleEdit?: (key: any) => void;
};

const TodoItem = ({
  title,
  content,
  check,
  handleDelete,
  handleEdit,
}: TodoItemProps) => {
  const [isCheck, setIsCheck] = useState(check);

  const handleInput = () => {
    setIsCheck((prev) => !prev);
  };

  useEffect(() => {
    setIsCheck(check);
  }, [title, content, check]);

  return (
    <div
      className={`${
        isCheck ? "bg-green-500	 " : "bg-red-500	 "
      } w-full flex p-2 items-center gap-4`}
    >
      <input
        type="checkbox"
        name="check"
        checked={isCheck}
        onClick={handleInput}
      />
      <div className="flex-1 justify-start text-start">
        <h1 className={isCheck ? "line-through" : ""}>{title}</h1>
        <p className={` italic`}>{content}</p>
      </div>
      <div className="flex flex-row gap-5">
        <button onClick={handleDelete}>Delete</button>
        <button onClick={handleEdit}>Edit</button>
      </div>
    </div>
  );
};

export default TodoItem;
