import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-expandable-accordion',
  templateUrl: './expandable-accordion.component.html',
  styleUrls: ['./expandable-accordion.component.scss']
})
export class ExpandableAccordionComponent {
  @Input() data: any[] = [];
  @Input() columns: { name: string; key: string }[] = []; 
  @Input() childColumns: { name: string; key: string }[] = []; 
  @Input() childKey: string = ''; 
  @Input() page: number = 0; 
  @Input() totalItems: number = 0;
  @Output() pageChange: EventEmitter<number> = new EventEmitter(); // Emit page change event

  // Toggle expansion for each item
  toggleItem(item: any) {
    item.expanded = !item.expanded;
  }

  // Handle page change
  onPageChange(newPage: number) {
    if (newPage >= 0 && newPage < Math.ceil(this.totalItems / 10)) {
      this.pageChange.emit(newPage); // Emit the page change event to the parent component
    }
  }
}
