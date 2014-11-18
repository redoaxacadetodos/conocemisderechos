package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(EjeController)
@Mock(Eje)
class EjeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/eje/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.ejeInstanceList.size() == 0
        assert model.ejeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.ejeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.ejeInstance != null
        assert view == '/eje/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/eje/show/1'
        assert controller.flash.message != null
        assert Eje.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/eje/list'

        populateValidParams(params)
        def eje = new Eje(params)

        assert eje.save() != null

        params.id = eje.id

        def model = controller.show()

        assert model.ejeInstance == eje
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/eje/list'

        populateValidParams(params)
        def eje = new Eje(params)

        assert eje.save() != null

        params.id = eje.id

        def model = controller.edit()

        assert model.ejeInstance == eje
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/eje/list'

        response.reset()

        populateValidParams(params)
        def eje = new Eje(params)

        assert eje.save() != null

        // test invalid parameters in update
        params.id = eje.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/eje/edit"
        assert model.ejeInstance != null

        eje.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/eje/show/$eje.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        eje.clearErrors()

        populateValidParams(params)
        params.id = eje.id
        params.version = -1
        controller.update()

        assert view == "/eje/edit"
        assert model.ejeInstance != null
        assert model.ejeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/eje/list'

        response.reset()

        populateValidParams(params)
        def eje = new Eje(params)

        assert eje.save() != null
        assert Eje.count() == 1

        params.id = eje.id

        controller.delete()

        assert Eje.count() == 0
        assert Eje.get(eje.id) == null
        assert response.redirectedUrl == '/eje/list'
    }
}
