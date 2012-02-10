package org.grozeille.dal;

import org.bson.types.ObjectId;
import org.grozeille.Invoice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, ObjectId> {

}
