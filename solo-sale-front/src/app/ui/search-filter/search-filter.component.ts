import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-search-filter',
  templateUrl: './search-filter.component.html',
  styleUrls: ['./search-filter.component.scss']
})
export class SearchFilterComponent {
  @Input() searchFields: any[] = []; // List of fields to display in the filter
  @Output() filterChanged: EventEmitter<any> = new EventEmitter(); // Emit event when filter changes

  onFilterChange() {
    const filters = this.searchFields.reduce((acc, field) => {
      acc[field.key] = field.value;
      return acc;
    }, {});
    this.filterChanged.emit(filters); // Emit filter changes to parent
  }

  onSearchChange() {
    this.onFilterChange(); // Trigger on filter change for search fields
  }
}
