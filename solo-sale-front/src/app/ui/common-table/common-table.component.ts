import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-common-table',
  templateUrl: './common-table.component.html',
  styleUrls: ['./common-table.component.scss']
})
export class CommonTableComponent {
  @Input() data: any[] = []; 
  @Input() columns: { name: string; key: string }[] = []; 
  @Input() page: number = 0; 
  @Input() totalItems: number = 0;
  @Output() pageChange: EventEmitter<number> = new EventEmitter();
  @Input() editAction: boolean = true; 
  @Output() openEditFormDialog: EventEmitter<any> = new EventEmitter(); 
  
  onPageChange(newPage: number) {
    if (newPage >= 0 && newPage < Math.ceil(this.totalItems / 10)) {
      this.pageChange.emit(newPage); // Emit the page change event to the parent component
    }
  }

  openEditDialog(item:any){
    this.openEditFormDialog.emit(item);
  }
}
