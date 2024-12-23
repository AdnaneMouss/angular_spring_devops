export interface Order {
  id: number;
  approved: boolean;
  quantity: number;
  deliveryDate: Date | null; // Use `Date` type for frontend
  ordererId: number; // ID of the user who placed the order
  supplierId: number; // ID of the supplier handling the order
  productId: number;
  productName: string;// ID of the product in the order
}
