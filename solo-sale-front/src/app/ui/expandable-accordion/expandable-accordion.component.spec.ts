import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpandableAccordionComponent } from './expandable-accordion.component';

describe('ExpandableAccordionComponent', () => {
  let component: ExpandableAccordionComponent;
  let fixture: ComponentFixture<ExpandableAccordionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ExpandableAccordionComponent]
    });
    fixture = TestBed.createComponent(ExpandableAccordionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
