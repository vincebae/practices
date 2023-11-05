mod ownership_test;
mod tuple_test;

fn main() {
    println!("Hello World");
    ownership_test::ownership_test();
    tuple_test::tuple_test();

    let n = 10;
    println!("{}", n);
}
