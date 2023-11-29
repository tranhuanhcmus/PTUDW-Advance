import { LegacyRef, useEffect, useRef, useState } from "react";

type Props = {
  isShow: boolean;
  handleClick: () => void;
  handleSubmit: (title: string, content: string) => void;
  content?: string;
  title?: string;
};

const TodoForm = ({
  isShow,
  handleClick,
  handleSubmit,
  content,
  title,
}: Props) => {
  const titleRef = useRef<HTMLInputElement>(null);
  const contentRef = useRef<HTMLInputElement>(null);

  const onSubmit = (e: any) => {
    e.preventDefault();

    handleSubmit(
      titleRef.current?.value || "",
      contentRef.current?.value || ""
    );
  };

  const onSubmitEdit = (e: any) => {
    e.preventDefault();
    handleSubmit(titleEdit || " ", contentEdit || "");
  };

  const [titleEdit, setTitle] = useState(title);
  const [contentEdit, setContent] = useState(content);

  useEffect(() => {
    setTitle(title);
    setContent(content);
  }, [title, content]);

  return (
    <>
      {isShow && (
        <form
          onSubmit={
            contentEdit !== undefined && titleEdit !== undefined
              ? onSubmitEdit
              : onSubmit
          }
          className="fixed inset-0"
        >
          <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" />
          <div className="fixed inset-0 z-10 w-screen overflow-y-auto">
            <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
              <div className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 sm:w-full sm:max-w-lg">
                <div className="bg-white px-4 pb-4 pt-5 sm:p-6 sm:pb-4">
                  <div
                    className={`rounded-md ring-gray-400 bg-slate-500 w-full flex p-2 items-center gap-4`}
                  >
                    {contentEdit !== undefined && titleEdit !== undefined ? (
                      <div className="flex-1 flex gap-2 flex-col justify-start text-start">
                        <input
                          className="block w-full rounded-sm p-2 bg-transparent text-black"
                          type="text"
                          name="title"
                          value={titleEdit}
                          onChange={(e) => setTitle(e.target.value)}
                        />
                        <input
                          className="block w-full rounded-sm p-2 bg-transparent text-black"
                          type="text"
                          name="content"
                          value={contentEdit}
                          onChange={(e) => setContent(e.target.value)}
                        />
                      </div>
                    ) : (
                      <div className="flex-1 flex gap-2 flex-col justify-start text-start">
                        <input
                          ref={titleRef}
                          className="block w-full rounded-sm p-2 bg-transparent text-black"
                          type="text"
                          name="title"
                          placeholder="Input Title"
                          required
                        />
                        <input
                          ref={contentRef}
                          className="block w-full rounded-sm p-2 bg-transparent text-black"
                          type="text"
                          name="content"
                          placeholder="Input Content"
                          required
                        />
                      </div>
                    )}
                  </div>
                </div>
                <div className="bg-gray-50 px-4 py-3 sm:flex sm:flex-row-reverse sm:px-6 gap-4">
                  <button type={"submit"}>Submit</button>
                  <button onClick={handleClick}>Cancel</button>
                </div>
              </div>
            </div>
          </div>
        </form>
      )}
    </>
  );
};

export default TodoForm;
