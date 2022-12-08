//Brianna Morreale
//12/8/2022

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.turtle.Instruction._
import doodle.random._
import scala.util.Random
import scala.collection.mutable.ListBuffer

sealed abstract class Instruction extends Product with Serializable
final case class Forward(distance: Double) extends Instruction
final case class Turn(angle: Angle) extends Instruction
final case class Branch(instructions: List[Instruction]) extends Instruction
final case class NoOp() extends Instruction
final case class TurtleState(at: Vec, heading: Angle)

object Turtle {
  import Image._

  def draw(instructions: List[Instruction]): Image = {
    def iterate(state: TurtleState, instructions: List[Instruction]): List[PathElement] =
      instructions match {
        case Nil => 
          Nil
        case i :: is =>
          val (newState, elements) = process(state, i)
          elements ++ iterate(newState, is)
      }

    def process(state: TurtleState, instruction: Instruction): (TurtleState, List[PathElement]) = {
      import PathElement._
      
      instruction match {
        case Forward(d) =>
          val nowAt = state.at + Vec.polar(d, state.heading)
          val element = lineTo(nowAt.toPoint)
    
          (state.copy(at = nowAt), List(element))
        case Turn(a) =>
          val nowHeading = state.heading + a
    
          (state.copy(heading = nowHeading), List())
        case Branch(is) =>
         val branchedElements = iterate(state, is)
         
         (state, moveTo(state.at.toPoint) :: branchedElements)
        case NoOp() =>
          (state, List())
      }
    }
    
    openPath(iterate(TurtleState(Vec.zero, Angle.zero), instructions))
  }

  def randomMovement(): Instruction ={
    val start = 0
    val end   = 4
    val rnd = new scala.util.Random
    val random = start + rnd.nextInt( (end - start) + 1 ) 
    print( random )

    if(random ==0){
        return Forward(10)
    }
    if(random ==1){
        return Turn(30.degrees)
     }
    if(random ==2){
        return Turn(90.degrees)
    }
    if(random ==3){
        return Forward(30)
    }
    if(random == 4){
      return Branch(List(Turn(10.degrees), Forward(10)))
    }
    return Forward(30)
}


  def main(args: Array[String]): Unit = {
    val instruction_list = new ListBuffer[Instruction]()
    for (a <- 1 to 100){
      instruction_list += randomMovement()
    }

    val instructions = instruction_list.toList

    val y = Turtle.draw(instructions)
        y.draw()
  }
}
