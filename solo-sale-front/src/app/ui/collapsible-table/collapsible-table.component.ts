import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-collapsible-table',
  templateUrl: './collapsible-table.component.html',
  styleUrls: ['./collapsible-table.component.scss']
})
export class CollapsibleTableComponent {
  @Input() data: any[] = []; // Parent row data
  @Input() childKey: string = ''; // The key where the child data is located
  @Input() columns: { name: string; key: string }[] = [];
  @Input() childColumns: { name: string; key: string }[] = [];
  @Input() page: number = 0; // Current page
  @Input() totalItems: number = 0; // Total number of items
  @Output() pageChange: EventEmitter<number> = new EventEmitter(); // Emit page change event

  // Toggles visibility of the child rows for a specific parent
  toggleRow(index: number) {
    console.log(this.data[index][this.childKey]);
    this.data[index].expanded = !this.data[index].expanded;
  }

  // Handle page change
  onPageChange(newPage: number) {
    if (newPage >= 0 && newPage < Math.ceil(this.totalItems / 10)) {
      this.pageChange.emit(newPage); // Emit the page change event to the parent component
    }
  }
}
