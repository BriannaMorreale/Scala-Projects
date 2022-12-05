//Brianna Morreale
//12/1/2022

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.random._


object generativeArt{

    val start = Random.always(Point.zero)

         Image
            .circle(50).noStroke.fillColor(Color.red)

    def step(current: Point): Random[Point] = {
        val drift = Point(current.x, current.y - 20) //changed the angle
        val noise =
        Random.normal(0.0, 5.0) flatMap { x => 
        Random.normal(0.0, 5.0) map { y =>
        Vec(x, y)
        }
    }

    noise.map(vec => drift + vec)
    }

    def render(point: Point): Image = {
        val length = (point - Point.zero).length
        val sides = (length / 20).toInt + 3 
        val hue = (length / 200).turns
        val color = Color.hsl(hue, 0.7, 0.5)

        Image
            .star(sides, 20, 5, 90.degrees) //changed the star shape and size
            .noFill
            .strokeColor(Color.green)
            .at(point.toVec)
    }

    def walk(steps: Int): Random[Image] = {
    def loop(count: Int, current: Point, image: Image): Random[Image] = {
        count match {
            case 0 => Random.always(image on render(current))

             case n =>
            val next = step(current)
                next.flatMap{ pt =>
                loop(count - 1, pt, image on render(current))
         }
        }
    }

    start.flatMap{ pt => loop(steps, pt, Image.empty) }
    }

    def christmasTree(particles: Int, steps: Int): Random[Image] = {
        particles match {
            case 0 => Random.always(Image.empty)
            case n => walk(steps).flatMap{ img1 =>
            christmasTree(n-1, steps) map { img2 =>
                img1 on img2
            }
            }
        }
        }


    def main(args: Array[String]): Unit = {
    //this makes a christmas tree and if you squint and turn your head you can see a star at the top
      christmasTree(50,30).run.draw()
        
    }
}