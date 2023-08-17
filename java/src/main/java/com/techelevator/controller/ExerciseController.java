package com.techelevator.controller;

import com.techelevator.dao.ExerciseDao;
import com.techelevator.model.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
public class ExerciseController {


    private ExerciseDao exerciseDao;
    @Autowired
    public ExerciseController(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    @RequestMapping(path = "/exercises/{exerciseId}", method = RequestMethod.GET)
    public ResponseEntity<Exercise> getExerciseById(@PathVariable int exerciseId) {
        Exercise exercise = exerciseDao.getExerciseByExerciseID(exerciseId);

        if (exercise != null) {
            return ResponseEntity.ok(exercise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void insertInitialExercises() {
            List<Exercise> initialExercises = Arrays.asList(
                new Exercise(0,"Chest Press Machine", "classpath:gym-equipment-images.gym-equipment-images/Chest Press Machine.webp", "Using the chest press machine is a straightforward process. Begin by ensuring the machine is upright and then follow these uncomplicated steps:\\n\\nAdjust the seat of the chest press to match your chest's height.\\n\\nAdd the required weights, preferably starting with a load that equals 25% of your body weight.\\n\\nActivate the foot pedal using your legs, which will move the handles to their initial position.\\n\\nPosition yourself against the padding of the chair, and extend the handles outward. Be cautious not to fully lock your elbows during this motion, and remember to exhale as you do.\\n\\nInhale and pull the handles back towards your chest.\\n\\nCongratulations, you've successfully completed one repetition. Repeat these actions to complete your set, aiding you in progressing toward your fitness goals.", " "),
                new Exercise(0,"Seated Dips", "classpath:gym-equipment-images.gym-equipment-images/Seated Dips.jpg", "Choose a weight, preferably a lighter one for a start. Modify your handles to suit your body's proportions. Sit down, ensuring your back is against the cushion. Stand up and push the weights downward while keeping your back against the seat. Lift your elbows and then lower the weights, but avoid lowering them too far.\"", ""),
                new Exercise(0,"Chest Fly Machine", "classpath:gym-equipment-images.gym-equipment-images/machine-chest-fly.jpg", "Customize the machine's settings to match your body's dimensions. Position your feet flat on the floor, sit in an upright posture, and let your body relax. Grasp the handles with your palms facing forward. Gradually bring your arms together in front of your chest, maintaining a slight bend in your elbows. Once your arms are fully closed in front of your chest, hold for a brief pause. Return your arms to the initial position, simultaneously opening up your chest while staying upright.", " "),
                new Exercise(0,"Flat Bench", "classpath:gym-equipment-images.gym-equipment-images/flat-bench.jpg", "The bench press, a popular piece of gym equipment, is particularly suitable for beginners in weight lifting. The process involves lying flat on a padded bench and performing push-ups using a barbell. Positioned above the bench is a barbell stand to hold the barbell. The bench press offers support and also enables the use of dumbbells. Maintaining proper posture and a flat back while lifting the barbell while lying down is crucial to avoid any strain on your spine. Though a fundamental exercise, ensuring a correct form is essential to prevent any potential back injuries.", ""),
                new Exercise(0,"Incline Bench", "classpath:gym-equipment-images.gym-equipment-images/incline-bench.jpg", "Similar to the bench press, the incline bench press maintains the same fundamental concept, with the key distinction being the ability to perform presses at an incline. By adjusting the back support to a higher angle, the incline bench press effectively targets the chest, biceps, and shoulders. It's important to exercise caution and avoid setting the bench at an overly steep incline, arching your back excessively, or locking your elbows. These actions could potentially result in severe injuries.", ""),
                new Exercise(0,"Adjustable-bench", "classpath:gym-equipment-images.gym-equipment-images/adjustable-bench.jpg", "An adaptable bench provides the advantages of a flat bench, incline bench, and decline bench press all in one. It enables you to reposition the bench to your desired angle for working out. Moreover, it permits an enhanced muscle stretch when employing dumbbells, contributing to a comprehensive workout experience.", ""),
                new Exercise(0,"Preacher Curl Machine", "classpath:gym-equipment-images.gym-equipment-images/preacher-curl.jpg", "To place special emphasis on building your biceps, you can engage in targeted exercises using a preacher curl bench. With this dedicated bench, you sit on a chair while your upper arms find support on an inclined surface in front of you. This setup allows you to perform bicep curls utilizing dumbbells, barbells, or alternative types of weights.", ""),
                new Exercise(0,"Arm Curl Machine", "classpath:gym-equipment-images.gym-equipment-images/arm-curl-machine.jpg", "The arm curl machine bears a resemblance to the preacher curl machine, yet the primary contrast lies in the presence of an integrated bar designed for cables or weights. This apparatus delivers steady resistance to your forearms and biceps, isolating the biceps and facilitating a complete range of motion.", ""),
                new Exercise(0,"Arm Extension Machine", "classpath:gym-equipment-images.gym-equipment-images/arm-extension.jpg", "With this machine, you can concentrate on your triceps by engaging them in exercises using resistance weights. It facilitates the extension and controlled return of your triceps to their original position. Additionally, certain machines even provide the opportunity to target your biceps as well.", ""),
                new Exercise(0,"Shoulder Press Machine", "classpath:gym-equipment-images.gym-equipment-images/shoulder-press.jpg", "Among the assortment of shoulder machines, the shoulder press stands out as the most widely used. This exercise entails sitting in an upright posture and employing your shoulder muscles to lift a weighted press. Typically, this involves lifting a level bar or handles. The machine offers controlled movement, allowing you to lift weights overhead without concerns of them falling, as might happen with dumbbells or barbells.", ""),
                new Exercise(0,"Lateral Raise Machine", "classpath:gym-equipment-images.gym-equipment-images/lateralraise.jpg", "For an alternative to dumbbell lateral raises, the lateral raise machine offers a suitable option. This machine features attached weights that are cable-driven during use. The motion involves raising and moving the rotating shoulder pads upwards and outwards. The machine's padded seat provides ample spinal support, reducing stress on various parts of your body as you work out.", ""),
                new Exercise(0,"Cable Row Machine", "classpath:gym-equipment-images.gym-equipment-images/Cable-Seated-Row-Back.jpeg", "This machine facilitates compound exercises by allowing you to engage multiple muscle groups simultaneously. During your workout, you'll be seated on a cushioned seat while gripping handles connected to a cable system. As you pull the handles, the cable in turn moves the weights. It's essential to ensure that your arms are fully extended above you, while maintaining a neutral alignment of your head and spine throughout the exercise.", ""),
                new Exercise(0,"Lat Pulldown", "classpath:gym-equipment-images.gym-equipment-images/lat-pulldown.jpg", "This machine permits you to engage in a compound exercise involving a pull-up/chin-up bar linked to a stack of weights, movable towards you while seated. It is particularly designed to target your lat muscles. However, it's important to avoid pulling the bar towards your stomach, as this indicates insufficient weight and incorrect muscle focus. Maintain an even grip on both sides of the bar and ensure it's not excessively wide for optimal results.", ""),
                new Exercise(0,"Ab Crunch Machine", "classpath:gym-equipment-images.gym-equipment-images/abdominalcrunch-1.jpg", "To introduce diversity to your regular crunches, consider incorporating the ab crunch machine. This machine enables you to concentrate and isolate your abdominal muscles. The remarkable feature of this equipment is the ability to target your abs while seated in a comfortable position. While performing this exercise, remember to maintain controlled breathing, inhaling and exhaling gradually. Also, ensure you bend your arms while using the ab crunch machine and that your back remains in contact with the cushioned seat.", ""),
                new Exercise(0,"Rotary Torso Machine", "classpath:gym-equipment-images.gym-equipment-images/rotarytorso-.jpg", "This machine facilitates torso twisting to both sides, emphasizing the importance of utilizing your abdominal muscles for the twisting motion rather than relying on your hips or legs.", ""),
                new Exercise(0,"Leg Press Machine", "classpath:gym-equipment-images.gym-equipment-images/linear-leg-press.jpg", "Utilizing the leg press machine aids in enhancing leg definition without requiring a spotter's assistance. This machine involves pressing your legs upward against a weight-loaded platform while your back is supported by a low, padded seat. It offers a valuable addition to your fitness routine, complementing exercises such as squats and lunges.", ""),
                new Exercise(0,"Leg Extension Machine", "classpath:gym-equipment-images.gym-equipment-images/leg-extension-machine.jpg", "The leg extension machine involves you sitting on a padded seat and using a lever or cable attached to weights to extend your legs. Out of the many machines that exist, this is one of the oldest and most effective for strengthening and toning your legs. To use the leg extension machine effectively, follow these steps:\n\nSit on the leg extension machine and put your feet underneath the pad. After this, grab the levers with your hands so you’re ready to begin.\n\nExtend your legs outwards and exhale while doing so.\n\nPause for a second, then slowly inhale as you return to the starting position.\n\nRepeat this for as many reps as you wish.", ""),
                new Exercise(0,"Leg Curl Machine", "classpath:gym-equipment-images.gym-equipment-images/legcurlp-.jpg", "This machine permits you to either lie or sit on a cushioned bench while curling your legs. The leg curl machine targets and strengthens the leg muscles in isolation. When utilizing this machine, ensure your leg curls are executed smoothly. Jerky movements during the exercise might indicate that the weights you're using are overly heavy.", ""),
                new Exercise(0,"Leg Abduction Machine", "classpath:gym-equipment-images.gym-equipment-images/leg-abduction.jpg", "The leg abduction and hip adduction machine provides resistance to your legs and hips while in a seated position. This exercise entails opening and closing your legs against weights. Notably, it targets and isolates your glutes and inner thigh muscles.", ""),
                new Exercise(0,"Seated Calf Raise", "classpath:gym-equipment-images.gym-equipment-images/calf-raise-seated.jpg", "Utilizing the seated calf machine entails sitting on a seat, elevating your toes on a platform, and exerting pressure on the pads with your knees. This exercise effectively isolates and enhances the calf muscle, while also contributing to the strengthening of the ankle joint.", ""),
                new Exercise(0,"Hack Squat", "classpath:gym-equipment-images.gym-equipment-images/hack-squat.jpg", "For added support during regular squats, the hack squat machine, also referred to as a squat rack, can be beneficial. This equipment enables better balance during squats and provides the option to incorporate more weight onto the power rack, effectively targeting and isolating your quadriceps.", ""),
                new Exercise(0,"Treadmill", "classpath:gym-equipment-images.gym-equipment-images/treadmil.jpg", "The treadmill stands as a valuable indoor machine that emulates running and walking, making it an excellent choice for cardio training. Treadmills come in various types, primarily electrical or manual. When using the treadmill, ensure you're wearing proper sneakers with sufficient grip. Before launching into a full treadmill workout, it's advisable to initiate with 3-5 minutes of brisk walking.", "")
        );
        exerciseDao.insertExercises(initialExercises);
    }

    @RequestMapping(path = "/exercises", method = RequestMethod.GET)
    public List<Exercise> getExerciseList() {
        return exerciseDao.getExerciseList();
    }

    @RequestMapping(path = "/session/{id}", method = RequestMethod.GET)
    public String getExerciseNameByExerciseId(@PathVariable Long id) {
        return exerciseDao.getExerciseNameByExerciseId(id);
    }
}
