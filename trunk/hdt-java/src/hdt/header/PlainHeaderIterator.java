/**
 * Revision: $Rev$
 * Last modified: $Date$
 * Last modified by: $Author$
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Contacting the authors:
 *   Mario Arias:               mario.arias@deri.org
 *   Javier D. Fernandez:       jfergar@infor.uva.es
 *   Miguel A. Martinez-Prieto: migumar2@infor.uva.es
 *   Alejandro Andres:          fuzzy.alej@gmail.com
 */
/**
 * 
 */
package hdt.header;

import hdt.exceptions.NotImplementedException;
import hdt.iterator.IteratorTripleString;
import hdt.triples.TripleString;

/**
 * @author mck
 *
 */
public class PlainHeaderIterator implements IteratorTripleString {
	private PlainHeader header;
	private int pos;
	private TripleString nextTriple, pattern, returnTriple;
	private boolean hasMoreTriples;
	
	/**
	 * 
	 */
	public PlainHeaderIterator(PlainHeader header, TripleString pattern) {
		this.header = header;
		this.pattern = pattern;
		this.pos = 0;
		
		doFetch();
	}
	
	private void doFetch() {
		do {
			getNextTriple();
		} while (hasMoreTriples && (!nextTriple.match(pattern)));
	}

	private void getNextTriple() {
		if(pos<header.triples.size()) {
			nextTriple = header.triples.get(pos);
		}
		
		pos++;
		
		hasMoreTriples = pos <= header.triples.size();
	}
	
	/* (non-Javadoc)
	 * @see hdt.iterator.IteratorTripleString#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return hasMoreTriples;
	}

	/* (non-Javadoc)
	 * @see hdt.iterator.IteratorTripleString#next()
	 */
	@Override
	public TripleString next() {
		returnTriple = nextTriple;
		doFetch();
		return returnTriple;
	}

	/* (non-Javadoc)
	 * @see hdt.iterator.IteratorTripleString#hasPrevious()
	 */
	@Override
	public boolean hasPrevious() {
		throw new NotImplementedException();
	}

	/* (non-Javadoc)
	 * @see hdt.iterator.IteratorTripleString#previous()
	 */
	@Override
	public TripleString previous() {
		throw new NotImplementedException();
	}

	/* (non-Javadoc)
	 * @see hdt.iterator.IteratorTripleString#goToStart()
	 */
	@Override
	public void goToStart() {
		pos=0;
		doFetch();
	}

	@Override
	public void remove() {
		throw new NotImplementedException();
	}

}