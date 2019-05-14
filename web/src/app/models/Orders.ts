export interface Order {
  id: number;
  orderDesc: string;
  firstName: string;
  lastName: string;
}

export interface PagenatedDetails<T> {
  items: T[];
  pageIndex: number;
  size: number;
  totalPages: number;
}
