import { User } from "./user";

export enum TaskStatus {
    PENDING = 'PENDING',
    IN_PROGRESS = 'IN_PROGRESS',
    COMPLETED = 'COMPLETED'
  }
  
  export interface Task {
    taskId: number;
    taskName: string;
    description: string;
    status: TaskStatus;
    assigned: User;
    createdAt: string;
    updatedAt: string;
  }