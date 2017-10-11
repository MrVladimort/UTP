/**
 *
 *  @author Hravchenko Vladyslav S15567
 *
 */

package zad1;


public interface Mapper <T, S> { // Uwaga: interfejs musi być sparametrtyzowany
	S map(T arg);
}  
