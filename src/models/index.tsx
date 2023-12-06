export type User={
	account:string,
	password:string,
}
export type Task = {
	idTask?:string,
	title: string,
	content: string,
	checked: boolean
}